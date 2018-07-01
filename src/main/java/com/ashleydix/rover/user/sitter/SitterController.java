package com.ashleydix.rover.user.sitter;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SitterController {
    public static final String SITTER_LIST_MODEL_ATTRIBUTE = "sitters";
    public static final String SITTER_LISTING_VIEW_NAME = "jsp/sitters";

    @VisibleForTesting
    @Autowired
    protected SitterRepository sitterRepository;

    @ModelAttribute("module")
    String module() {
        return "sitters";
    }


    @GetMapping("/")
    String index(Model model) {
        List<Sitter> sitters = sitterRepository.findAll();
        sitters.sort((o1, o2) -> Double.compare(o2.getSitterRank(), o1.getSitterRank()));
        model.addAttribute(SITTER_LIST_MODEL_ATTRIBUTE, sitters);
        return SITTER_LISTING_VIEW_NAME;

    }
}
