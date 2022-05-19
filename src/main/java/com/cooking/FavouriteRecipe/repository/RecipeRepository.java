package com.cooking.FavouriteRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooking.FavouriteRecipe.entity.RecipeEntity;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>{

}

