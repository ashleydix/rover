package com.ashleydix.rover.reviews;

import com.ashleydix.rover.base.BaseRepository;
import com.ashleydix.rover.user.sitter.Sitter;

import java.util.List;

public interface ReviewRepository extends BaseRepository<Review, Long> {

    List<Review> findAllBySitter(Sitter sitter);
}
