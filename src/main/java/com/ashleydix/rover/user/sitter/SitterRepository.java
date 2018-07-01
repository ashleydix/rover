package com.ashleydix.rover.user.sitter;

import com.ashleydix.rover.user.BaseUserRepository;

public interface SitterRepository extends BaseUserRepository<Sitter> {
    Sitter findOneByEmail(String email);
}
