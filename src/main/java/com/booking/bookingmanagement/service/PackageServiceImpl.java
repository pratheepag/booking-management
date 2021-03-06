package com.booking.bookingmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.booking.bookingmanagement.model.Packages;
import com.booking.bookingmanagement.repository.PackageRepository;

@Service("packageService")
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageRepository packageRepository;
	
	public Packages save(Packages packages) {
		// TODO Auto-generated method stub
		packageRepository.saveAndFlush(packages);
		return null;
	}

	/*public List<Packages> findAllPackages() {
		// TODO Auto-generated method stub
		return packageRepository.findAll();
	}
*/
	public void delete(Long id) {
		// TODO Auto-generated method stub
		packageRepository.deleteById(id); 
	}

	public Optional<Packages> findPackage(Long id) {
		// TODO Auto-generated method stub
		return packageRepository.findById(id);
	}

	@Override
	public Page<Packages> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return packageRepository.findAll(pageable);
	
	}

	@Override
	public List<Packages> findAllPackages() {
		// TODO Auto-generated method stub
		return packageRepository.findAll();
	}

}
