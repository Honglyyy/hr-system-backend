package com.ly.hrsystembackend.model;

import com.ly.hrsystembackend.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees_attendance")
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employeeProfile;

    @Column(name = "date")
    private Date date;

    @Column(name = "time_in")
    private Instant timeIn;

    @Column(name = "time_out")
    private Instant timeOut;

    @Column(name = "attendance_status")
    private AttendanceStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
