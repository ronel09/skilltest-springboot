package service;


import java.time.LocalDateTime;
import java.util.List;

import entity.Learner;
import exception.AttendanceException;
import repository.LearnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


	
@Service
public class AttendanceService {
	@Autowired
	private LearnerRepository repository;
	
	 public Learner timeIn(Learner learner) {
	        Learner existingLearner = repository.findByEid(learner.getEid());
	        if (existingLearner.getTimeIn() == null) {
	            existingLearner.setTimeIn(LocalDateTime.now());
	            return repository.save(existingLearner);
	        } else {
	            throw new AttendanceException("Learner already timed-out");
	        }
	    }

	    public Learner timeOut(Learner learner) {
	        Learner existingLearner = repository.findByEid(learner.getEid());
	        if (existingLearner.getTimeOut() == null) {
	            if (existingLearner.getTimeIn() == null) {
	                throw new AttendanceException("Learner not yet timed-in");
	            } else {
	                existingLearner.setTimeOut(LocalDateTime.now());
	                return repository.save(existingLearner);
	            }
	        } else {
	            throw new AttendanceException("Learner already timed-out");
	        }
	    }

	    public List<Learner> getLearners() {
	        return repository.findAll();
	    }

	    public Learner findByEid(String eid) {
	        return repository.findByEid(eid);
	    }
	}