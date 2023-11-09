package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.DrinkEditDto;
import com.example.demo_project.model.dto.LunchAddDTO;
import com.example.demo_project.model.dto.LunchEditDTO;
import com.example.demo_project.model.entity.DrinkEntity;
import com.example.demo_project.model.entity.LunchEntity;
import com.example.demo_project.repository.LunchRepository;
import com.example.demo_project.service.LunchService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LunchServiceImpl implements LunchService {

    private final LunchRepository lunchRepository;
    private final ModelMapper modelMapper;

    public LunchServiceImpl(LunchRepository lunchRepository, ModelMapper modelMapper) {
        this.lunchRepository = lunchRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<LunchEntity> getAllLunches() {
        return lunchRepository.findAll();
    }

    @Override
    public void addLunch(LunchAddDTO lunchAddDTO) {

        Optional<LunchEntity> lunch = this.lunchRepository.findByName(lunchAddDTO.getName());

        if (lunch.isPresent()) {
            LunchEntity existingLunch = lunch.get();

            existingLunch.setName(lunchAddDTO.getName());
            existingLunch.setDescription(lunchAddDTO.getDescription());
            existingLunch.setPhoto(lunchAddDTO.getPhoto());
            existingLunch.setPrice(lunchAddDTO.getPrice());

            lunchRepository.save(existingLunch);

            LunchEntity lunchEntity = this.modelMapper.map(lunch, LunchEntity.class);
            lunchRepository.save(lunchEntity);
        }else {
            LunchEntity newLunch = this.modelMapper.map(lunchAddDTO, LunchEntity.class);
            lunchRepository.save(newLunch);
        }

    }

    @Override
    public LunchEditDTO getLunchById(Long id) {
        Optional<LunchEntity> lunchEntityOptional = lunchRepository.findById(id);
        if (lunchEntityOptional.isPresent()) {
            LunchEntity lunchEntity = lunchEntityOptional.get();
            return convertEntityToEditLunchDto(lunchEntity);
        }
        return null;
    }

    @Override
    public LunchEditDTO getLunchEditDtoById(Long id) {
        Optional<LunchEntity> lunchEntityOptional = lunchRepository.findById(id);
        return lunchEntityOptional.map(lunchEntity -> modelMapper.map(lunchEntity, LunchEditDTO.class)).orElse(null);
    }

    @Override
    public void saveLunch(LunchEditDTO lunchEditDTO) {
      LunchEntity lunchEntity = modelMapper.map(lunchEditDTO, LunchEntity.class);

      lunchRepository.save(lunchEntity);

    }

    private LunchEditDTO convertEntityToEditLunchDto(LunchEntity lunchEntity) {

        LunchEditDTO editLunch = new LunchEditDTO();

        editLunch.setId(lunchEntity.getId());
        editLunch.setName(lunchEntity.getName());
        editLunch.setDescription(lunchEntity.getDescription());
        editLunch.setPhoto(lunchEntity.getPhoto());
        editLunch.setPrice(lunchEntity.getPrice());
        return editLunch;
    }
}
