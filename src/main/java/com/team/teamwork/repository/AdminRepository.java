package com.team.teamwork.repository;

import com.team.teamwork.entity.Admin;
import com.team.teamwork.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
