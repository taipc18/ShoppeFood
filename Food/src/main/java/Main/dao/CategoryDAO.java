package Main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, String>{

}
