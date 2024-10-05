package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoQueryRepository {

    /*@Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);*/

  /*  @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);*/

    /*@Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u WHERE (:weather IS NULL OR t.weather LIKE %:weather%) " +
            "AND t.modifiedAt BETWEEN :startDate AND :endDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllBetweenStartDateAndEndDate(Pageable pageable, String weather, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u " +
            "WHERE (:weather IS NULL OR t.weather LIKE %:weather%) ORDER BY t.modifiedAt DESC ")
    Page<Todo> findAllWithWeather(Pageable pageable, String weather);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u WHERE (:weather IS NULL OR t.weather LIKE %:weather%) " +
            "AND t.modifiedAt >= :startDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllAfterStartDate(Pageable pageable, String weather, LocalDateTime startDate);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u WHERE (:weather IS NULL OR t.weather LIKE %:weather%) " +
            "AND t.modifiedAt <= :endDate ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllBeforeEndDate(Pageable pageable, String weather, LocalDateTime endDate);*/

    @Query("SELECT t FROM Todo t " +
           "LEFT JOIN FETCH t.user u WHERE (:weather IS NULL OR t.weather LIKE %:weather%) " +
            "AND (:startDate IS NULL OR t.modifiedAt >= :startDate) " +
            "AND (:endDate IS NULL OR t.modifiedAt <= :endDate)" +
            "ORDER BY t.modifiedAt DESC ")
    Page<Todo> findAllWithCondition(Pageable pageable, String weather, LocalDateTime startDate, LocalDateTime endDate);
}
