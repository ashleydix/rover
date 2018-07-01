package com.ashleydix.rover.user.sitter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SitterControllerTest {

    @Mock
    private SitterRepository sitterRepository;
    @Mock
    private Model model;

    private SitterController controller;
    private List<Sitter> sitters;

    @Captor
    ArgumentCaptor<List<Sitter>> sitterListCaptor;

    @Before
    public void setup(){
        controller = new SitterController();
        controller.sitterRepository = sitterRepository;

        sitters = new ArrayList<>();
        sitters.add(generateSitter("Bob", 1.0));
        sitters.add(generateSitter("John", 5.0));
        sitters.add(generateSitter("Mickey Mouse", 3.0));
        when(sitterRepository.findAll()).thenReturn(sitters);
    }

    @Test
    public void testIndex() {
        String viewName = controller.index(model);
        assertEquals(SitterController.SITTER_LISTING_VIEW_NAME, viewName);
        verify(model, times(1))
                .addAttribute(eq(SitterController.SITTER_LIST_MODEL_ATTRIBUTE), sitterListCaptor.capture());
        List<Sitter> capturedSitterList = sitterListCaptor.getValue();
        assertEquals("John", capturedSitterList.get(0).getName());
        assertEquals("Mickey Mouse", capturedSitterList.get(1).getName());
        assertEquals("Bob", capturedSitterList.get(2).getName());
    }


    private Sitter generateSitter(String name, double sitterRank) {
        Sitter sitter = new Sitter();
        sitter.setName(name);
        sitter.setSitterRank(sitterRank);
        return sitter;
    }

}