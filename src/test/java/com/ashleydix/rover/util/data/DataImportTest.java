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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataImportTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private SitterRepository sitterRepository;
    @Mock
    private DogRepository dogRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private DataImport dataImport;

    @Captor
    ArgumentCaptor<Dog> dogCaptor;
    @Captor
    ArgumentCaptor<User> userCaptor;
    @Captor
    ArgumentCaptor<Sitter> sitterCaptor;
    @Captor
    ArgumentCaptor<Booking> bookingCaptor;
    @Captor
    ArgumentCaptor<Review> reviewCaptor;

    @Test
    public void testImport() throws FileNotFoundException {
        InputStream input = new FileInputStream("src/test/resources/testReviews1.csv");

        //----------Mock Users------------
        when(userRepository.exists("user3444@t-mobile.com")).thenReturn(false).thenReturn(true);

        //----------Mock Sitters----------
        when(sitterRepository.exists("ashley@ashleydix.com")).thenReturn(false).thenReturn(true);
        when(sitterRepository.exists("user7508@t-mobile.com")).thenReturn(false).thenReturn(true);
        when(sitterRepository.findOneByEmail("user7508@t-mobile.com")).thenReturn(new Sitter("user7508@t-mobile.com", "Leilani R.", "", ""));

        //----------Mock Dogs-------------
        when(dogRepository.exists(any(User.class), eq("Minnie"))).thenReturn(false).thenReturn(true);
        when(dogRepository.exists(any(User.class), eq("Moby"))).thenReturn(false).thenReturn(true);
        when(dogRepository.exists(any(User.class), eq("Coco"))).thenReturn(false).thenReturn(true);
        when(dogRepository.findOneByOwnerAndName(any(User.class), anyString())).thenAnswer(
                (Answer<Dog>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return new Dog((String) args[1], (User) args[0]);
                }
        );

        dataImport.constructObjects(input, true);

        //----------Test Users------------
        verify(userRepository, times(1)).saveAndFlush(userCaptor.capture());
        verify(userRepository, times(3)).exists(anyString());
        List<User> capturedUsers = userCaptor.getAllValues();
        assertEquals(1, capturedUsers.size());
        assertEquals("Nancy L.", capturedUsers.get(0).getName());

        //----------Test Sitters----------
        verify(sitterRepository, times(2)).saveAndFlush(sitterCaptor.capture());
        verify(sitterRepository, times(3)).exists(anyString());
        verify(sitterRepository, times(3)).save(any(Sitter.class));
        List<Sitter> capturedSitters = sitterCaptor.getAllValues();
        assertEquals(2, capturedSitters.size());
        capturedSitters.sort(Comparator.comparing(Sitter::getName));
        assertEquals("Ashley D.", capturedSitters.get(0).getName());
        assertEquals("Leilani R.", capturedSitters.get(1).getName());

        //----------Test Dogs-------------
        verify(dogRepository, times(7)).exists(any(User.class), anyString());
        verify(dogRepository, times(3)).saveAndFlush(dogCaptor.capture());
        List<Dog> capturedDogs = dogCaptor.getAllValues();
        capturedDogs.sort(Comparator.comparing(Dog::getName));
        assertEquals(3, capturedDogs.size());
        assertEquals("Coco", capturedDogs.get(0).getName());
        assertEquals("Minnie", capturedDogs.get(1).getName());
        assertEquals("Moby", capturedDogs.get(2).getName());

        //----------Test Reviews----------
        verify(reviewRepository, times(3)).saveAndFlush(reviewCaptor.capture());
        List<Review> capturedReviews = reviewCaptor.getAllValues();
        assertEquals(3, capturedReviews.size());
        assertEquals(5, capturedReviews.get(0).getRating());
        assertEquals(2, capturedReviews.get(1).getRating());
        assertEquals(4, capturedReviews.get(2).getRating());

        //----------Test Bookings---------
        verify(bookingRepository, times(3)).saveAndFlush(bookingCaptor.capture());
        List<Booking> capturedBookings = bookingCaptor.getAllValues();
        assertEquals(3, capturedBookings.size());
        assertEquals(2, capturedBookings.get(0).getDogs().size());
        assertEquals("Leilani R.", capturedBookings.get(0).getSitter().getName());
        assertEquals(3, capturedBookings.get(1).getDogs().size());
        assertEquals("Leilani R.", capturedBookings.get(1).getSitter().getName());
        assertEquals(2, capturedBookings.get(2).getDogs().size());
        assertEquals("Ashley D.", capturedBookings.get(2).getSitter().getName());
    }
}
