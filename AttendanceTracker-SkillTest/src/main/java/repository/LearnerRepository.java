package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Learner;

public interface LearnerRepository extends JpaRepository<Learner, String> {
	 Learner findByEid(String eid);
}
