package com.example.demo_project.web;
import com.example.demo_project.model.dto.LunchAddDTO;
import com.example.demo_project.model.dto.LunchEditDTO;
import com.example.demo_project.model.dto.DinnerAddDTO;
import com.example.demo_project.model.dto.DinnerEditDTO;
import com.example.demo_project.model.entity.LunchEntity;
import com.example.demo_project.model.entity.DinnerEntity;
import com.example.demo_project.service.LunchService;
import com.example.demo_project.service.DinnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodControllerTest {

    @Mock
    private LunchService lunchService;


    @Mock
    private DinnerService dinnerService;

    @InjectMocks
    private FoodController foodController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowLunch() {
        List<LunchEntity> mockLunches = new ArrayList<>();
        when(lunchService.getAllLunches()).thenReturn(mockLunches);

        Model model = mock(Model.class);
        ModelAndView modelAndView = foodController.showLunch(model);

        assertNotNull(modelAndView);
        assertEquals("lunches", modelAndView.getViewName());

        verify(model, times(1)).addAttribute("myLunches", mockLunches);
    }

    @Test
    void testAddLunchWithValidInput() {
        LunchAddDTO lunchAddDTO = new LunchAddDTO();
        BindingResult bindingResult = mock(BindingResult.class);

        ModelAndView modelAndView = foodController.addLunch(lunchAddDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/lunches", modelAndView.getViewName());

        verify(lunchService, times(1)).addLunch(lunchAddDTO);
    }

    @Test
    void testAddLunchWithInvalidInput() {
        LunchAddDTO lunchAddDTO = new LunchAddDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = foodController.addLunch(lunchAddDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("add_lunches", modelAndView.getViewName());

        verify(lunchService, never()).addLunch(any());
    }
    @Test
    void testShowEditLunchForm() {
        Long lunchId = 1L;
        LunchEditDTO mockLunch = new LunchEditDTO();
        when(lunchService.getLunchById(lunchId)).thenReturn(mockLunch);

        Model model = mock(Model.class);
        ModelAndView modelAndView = foodController.showEditLunchForm(lunchId, model);

        assertNotNull(modelAndView);
        assertEquals("edit_lunch", modelAndView.getViewName());

        verify(model, times(1)).addAttribute("lunch", mockLunch);
    }
    @Test
    void testEditLunchWithValidInput() {
        Long lunchId = 1L;
       LunchEditDTO lunchEditDTO = new LunchEditDTO();
        BindingResult bindingResult = mock(BindingResult.class);

        when(lunchService.getLunchEditDtoById(lunchId)).thenReturn(lunchEditDTO);

        doAnswer(invocation -> {
            LunchEditDTO savedLunch = invocation.getArgument(0);
            return null;
        }).when(lunchService).saveLunch(any());

        ModelAndView modelAndView = foodController.editLunch(lunchId, lunchEditDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/lunches", modelAndView.getViewName());

        verify(lunchService, times(1)).getLunchEditDtoById(lunchId);
        verify(lunchService, times(1)).saveLunch(any());
    }
    @Test
    void testEditLunchWithInvalidInput() {
        Long lunchId = 1L;
        LunchEditDTO lunchEditDTO = new LunchEditDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = foodController.editLunch(lunchId, lunchEditDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/edit_lunch", modelAndView.getViewName());

        verify(lunchService, never()).getLunchEditDtoById(lunchId);
        verify(lunchService, never()).saveLunch(any());
    }

    @Test
    void testShowDinner() {
        List<DinnerEntity> mockDinners = new ArrayList<>();
        when(dinnerService.getAllDinners()).thenReturn(mockDinners);

        Model model = mock(Model.class);
        ModelAndView modelAndView = foodController.showDinner(model);

        assertNotNull(modelAndView);
        assertEquals("dinners", modelAndView.getViewName());

        verify(model, times(1)).addAttribute("myDinners", mockDinners);
    }

    @Test
    void testAddDinnerWithValidInput() {
        DinnerAddDTO dinnerAddDTO = new DinnerAddDTO();
        BindingResult bindingResult = mock(BindingResult.class);

        ModelAndView modelAndView = foodController.addDinner(dinnerAddDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/dinners", modelAndView.getViewName());

        verify(dinnerService, times(1)).addDinner(dinnerAddDTO);
    }

    @Test
    void testAddDinnerWithInvalidInput() {
        DinnerAddDTO dinnerAddDTO = new DinnerAddDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = foodController.addDinner(dinnerAddDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("add_dinners", modelAndView.getViewName());

        verify(dinnerService, never()).addDinner(any());
    }

    @Test
    void testShowEditDinnerForm() {
        Long dinnerId = 1L;
        DinnerEditDTO mockDinner = new DinnerEditDTO();
        when(dinnerService.getDinnerById(dinnerId)).thenReturn(mockDinner);

        Model model = mock(Model.class);
        ModelAndView modelAndView = foodController.showEditDinnerForm(dinnerId, model);

        assertNotNull(modelAndView);
        assertEquals("edit_dinner", modelAndView.getViewName());

        verify(model, times(1)).addAttribute("dinner", mockDinner);
    }

    @Test
    void testEditDinnerWithValidInput() {
        Long dinnerId = 1L;
        DinnerEditDTO dinnerEditDTO = new DinnerEditDTO();
        BindingResult bindingResult = mock(BindingResult.class);

        when(dinnerService.getDinnerEditDtoById(dinnerId)).thenReturn(dinnerEditDTO);


        doAnswer(invocation -> {
            DinnerEditDTO savedDinner = invocation.getArgument(0);
            return null;
        }).when(dinnerService).saveDinner(any());

        ModelAndView modelAndView = foodController.editDinner(dinnerId, dinnerEditDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/dinners", modelAndView.getViewName());

        verify(dinnerService, times(1)).getDinnerEditDtoById(dinnerId);
        verify(dinnerService, times(1)).saveDinner(any());
    }

    @Test
    void testEditDinnerWithInvalidInput() {
        Long dinnerId = 1L;
        DinnerEditDTO dinnerEditDTO = new DinnerEditDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = foodController.editDinner(dinnerId, dinnerEditDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/edit_dinner", modelAndView.getViewName());

        verify(dinnerService, never()).getDinnerEditDtoById(dinnerId);
        verify(dinnerService, never()).saveDinner(any());
    }

}