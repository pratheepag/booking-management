package com.booking.bookingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.bookingmanagement.model.Category;
import com.booking.bookingmanagement.model.Packages;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
