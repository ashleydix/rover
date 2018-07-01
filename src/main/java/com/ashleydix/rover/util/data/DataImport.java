package com.ashleydix.rover.util.data;

import com.ashleydix.rover.booking.Booking;
import com.ashleydix.rover.booking.BookingRepository;
import com.ashleydix.rover.dogs.Dog;
import com.ashleydix.rover.dogs.DogRepository;
import com.ashleydix.rover.reviews.Review;
import com.ashleydix.rover.reviews.ReviewRepository;
import com.ashleydix.rover.user.User;
import com.ashleydix.rover.user.UserRepository;
import com.ashleydix.rover.user.sitter.Sitter;
import com.ashleydix.rover.user.sitter.SitterRepository;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataImport {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataImport.class);

    @Autowired
    @VisibleForTesting
    protected UserRepository userRepository;
    @Autowired
    @VisibleForTesting
    protected SitterRepository sitterRepository;
    @Autowired
    @VisibleForTesting
    protected DogRepository dogRepository;
    @Autowired
    @VisibleForTesting
    protected BookingRepository bookingRepository;
    @Autowired
    @VisibleForTesting
    protected ReviewRepository reviewRepository;

    @PostConstruct
	public void importCSV() throws FileNotFoundException {
        InputStream input = new FileInputStream("src/main/resources/reviews.csv");
        constructObjects(input, true);
	}

	@VisibleForTesting
	protected void constructObjects(InputStream input, boolean hasHeader){
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            int lineCount = 1;
            while ((line = br. readLine()) != null) {
                if(hasHeader){
                    hasHeader = false;
                    continue;
                }
/*

                0 - rating
                1 - sitter_image
                2 - end_date
                3 - text
                4 - owner_image
                5 - dogs
                6 - sitter
                7 - owner
                8 - start_date
                9 - sitter_phone_number
                10 - sitter_email
                11 - owner_phone_number
                12 - owner_email

*/

                // use comma as separator
                String[] reviewInfo = line.split(",");

                // format date times
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate startDate = LocalDate.parse(reviewInfo[8], formatter);
                LocalDate endDate = LocalDate.parse(reviewInfo[2], formatter);
                LocalDateTime endDateTime = endDate.atTime(0,0,0,0);

                //-------------CreateOwner-----------------
                User owner = new User(reviewInfo[12], reviewInfo[7], reviewInfo[11], reviewInfo[4]);

                if(!userRepository.exists(owner.getEmail())) {
                    userRepository.saveAndFlush(owner);
                } else {
                    owner = userRepository.findOneByEmail(owner.getEmail());
                }

                //-------------CreateSitter---------------
                Sitter sitter = new Sitter(reviewInfo[10], reviewInfo[6], reviewInfo[9], reviewInfo[1]);
                if(!sitterRepository.exists(sitter.getEmail())){
                    sitterRepository.saveAndFlush(sitter);
                } else {
                    sitter = sitterRepository.findOneByEmail(sitter.getEmail());
                }

                //-------------CreateBooking--------------
                Booking booking = new Booking(owner, sitter, startDate, endDate);

                //-------------CreateDogs-----------------
                String[] dogNames = reviewInfo[5].split("\\|");
                for(String dogName: dogNames){
                    Dog dog;
                    if(!dogRepository.exists(owner, dogName)) {
                        dog = new Dog(dogName, owner);
                        dogRepository.saveAndFlush(dog);
                    }
                    dog = dogRepository.findOneByOwnerAndName(owner, dogName);
                    booking.getDogs().add(dog);
                }

                //-------------SaveBooking----------------
                bookingRepository.saveAndFlush(booking);

                //-------------CreateReview---------------
                Review review = new Review(owner, sitter, booking, reviewInfo[3], Integer.parseInt(reviewInfo[0]));
                review.setReviewDate(endDateTime);
                sitter.addRating(review.getRating());

                //-------------SaveSitter-----------------
                sitterRepository.save(sitter);

                //-------------SaveReview-----------------
                reviewRepository.saveAndFlush(review);
                log.info("Imported " + lineCount + " rows.");
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
