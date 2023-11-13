package org.project.backapi.controller;

import jakarta.validation.Valid;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.ReportDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.service.CommentService;
import org.project.backapi.service.ReportService;
import org.project.backapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReportService reportService;

    @Autowired
    CommentService commentService;

    /*@PostMapping("/posts/{postId}/reports")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    public ResponseEntity<?> create(@RequestBody @Valid ReportDto dto, @PathVariable Long postID) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        ReportDto createdReport = reportService.create(dto, postID, currentUser);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Report created successfully");
        response.put("report", createdReport);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
/*@GetMapping("/{postId}/comments")
    public ResponseEntity<?> getAllCommentsByPostId(
            @PathVariable Long postId,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId, page, size), HttpStatus.OK);
    }
    @GetMapping("/reports/{reportId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportDto> readAReport(@PathVariable Long reportId) {

        return new ResponseEntity<>(reportService.get(reportId), HttpStatus.OK);
    }*/

    @GetMapping("/reports")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PagedResponse<ReportDto>> readAllReports(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        PagedResponse<ReportDto> reports = reportService.getAll(page, size, false);
        return ResponseEntity.ok(reports);
    }

    // Fetch all visible reports
    @GetMapping("/reports/visible")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PagedResponse<ReportDto>> getAllVisibleReports(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<ReportDto> reports = reportService.getAll(page, size, true);

        return ResponseEntity.ok(reports);
    }

}
