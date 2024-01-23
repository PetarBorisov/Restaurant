package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.DinnerAddDTO;
import com.example.demo_project.model.dto.DinnerEditDTO;

import com.example.demo_project.model.entity.DinnerEntity;
import com.example.demo_project.repository.DinnerRepository;
import com.example.demo_project.service.DinnerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DinnerServiceImpl implements DinnerService {

    private final DinnerRepository dinnerRepository;
    private final ModelMapper modelMapper;

    public DinnerServiceImpl(DinnerRepository dinnerRepository, ModelMapper modelMapper) {
        this.dinnerRepository = dinnerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<DinnerEntity> getAllDinners() {
        return this.dinnerRepository.findAll();
    }

    @Override
    public void addDinner(DinnerAddDTO dinnerAddDTO) {

        Optional<DinnerEntity> dinner = this.dinnerRepository.findByName(dinnerAddDTO.getName());

        if (dinner.isPresent()) {
            DinnerEntity existingDinner = dinner.get();

            existingDinner.setName(dinnerAddDTO.getName());
            existingDinner.setDescription(dinnerAddDTO.getDescription());
            existingDinner.setPhoto(dinnerAddDTO.getPhoto());
            existingDinner.setPrice(dinnerAddDTO.getPrice());

            dinnerRepository.save(existingDinner);

            DinnerEntity dinnerEntity = this.modelMapper.map(dinner, DinnerEntity.class);
            dinnerRepository.save(dinnerEntity);
        }else {
            DinnerEntity newDinner = this.modelMapper.map(dinnerAddDTO, DinnerEntity.class);
            dinnerRepository.save(newDinner);
        }

    }

    @Override
    public DinnerEditDTO getDinnerById(Long id) {
        Optional<DinnerEntity> dinnerEntityOptional = dinnerRepository.findById(id);
        if (dinnerEntityOptional.isPresent()) {
            DinnerEntity dinnerEntity = dinnerEntityOptional.get();
            return convertEntityToEditLunchDto(dinnerEntity);
        }
        return null;
    }

  @Override
    public DinnerEditDTO getDinnerEditDtoById(Long id) {
        Optional<DinnerEntity> dinnerEntityOptional = dinnerRepository.findById(id);
        return dinnerEntityOptional.map(dinnerEntity -> modelMapper.map(dinnerEntity, DinnerEditDTO.class)).orElse(null);
    }

    @Override
    public void saveDinner(DinnerEditDTO dinnerEditDTO) {

        DinnerEntity dinnerEntity = modelMapper.map(dinnerEditDTO, DinnerEntity.class);

        dinnerRepository.save(dinnerEntity);
    }

    @Override
    public void editDinner(Long id, DinnerEditDTO dinnerEditDTO) {
        Optional<DinnerEntity> dinner = this.dinnerRepository.findById(dinnerEditDTO.getId());

        if (dinner.isPresent()) {
            DinnerEntity existingDinner = dinner.get();
            existingDinner.setName(dinnerEditDTO.getName());
            existingDinner.setPhoto(dinnerEditDTO.getPhoto());
            existingDinner.setDescription(dinnerEditDTO.getDescription());
            existingDinner.setPrice(dinnerEditDTO.getPrice());

            dinnerRepository.save(existingDinner);
        }
    }

    @Override
    public void deleteDinner(Long id) {
        Optional<DinnerEntity> delete = this.dinnerRepository.findById(id);
        delete.ifPresent(dinnerRepository::delete);
    }

    private DinnerEditDTO convertEntityToEditLunchDto(DinnerEntity dinnerEntity) {

        DinnerEditDTO editDinner = new DinnerEditDTO();

        editDinner.setId(dinnerEntity.getId());
        editDinner.setName(dinnerEntity.getName());
        editDinner.setDescription(dinnerEntity.getDescription());
        editDinner.setPhoto(dinnerEntity.getPhoto());
        editDinner.setPrice(dinnerEntity.getPrice());

        return editDinner;
    }
}
