package com.booking.bookingmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import com.booking.bookingmanagement.model.Packages;

public interface PackageService {
	Packages save(Packages packages);

	List<Packages> findAllPackages();
	
	void delete(Long id);
	
	Optional<Packages> findPackage(Long id);

	 Page<Packages> findAll(org.springframework.data.domain.Pageable pageable);
}
