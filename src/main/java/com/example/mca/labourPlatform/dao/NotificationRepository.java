package com.example.mca.labourPlatform.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mca.labourPlatform.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
