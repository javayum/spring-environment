package net.javayum.spring.environment.property.datasource.jpa;

import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.Property;
import net.javayum.spring.environment.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyRepositoryJPA implements PropertyRepository {

    private final PropertyRepositorySpringData repository;

    @Autowired
    public PropertyRepositoryJPA(PropertyRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public List<Property> findAll() {

        List<Property> properties = new ArrayList<>();

        List<PropertyEntity> entities = repository.findAll();

        for ( PropertyEntity entity: entities) {
            properties.add(entity);
        }

        return properties;
    }

    @Override
    public Property findOne(Key key) {
        return repository.findOne(key.toStringValue());
    }

    @Override
    public Property requireOne(Key key) throws NotFoundException {

        if ( repository.exists(key.toStringValue())) {
            return findOne(key);
        } else {
            throw new NotFoundException(key);
        }
    }

    @Override
    public Property save(Property property) {

        PropertyEntity entity;

        if ( property instanceof PropertyEntity) {
            entity = (PropertyEntity) property;
        } else {
            entity = PropertyEntity.of(property.getKey(), property.getValue());
        }

        return repository.save(entity);
    }

    @Override
    public void delete(Key key) {

        repository.delete(key.toStringValue());
    }
}
