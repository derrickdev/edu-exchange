package org.project.backapi.service;

import org.project.backapi.converter.ReportConverter;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.Report;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.ReportDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.exception.RejectedOperationException;
import org.project.backapi.repository.PostRepository;
import org.project.backapi.repository.ReportRepository;
import org.project.backapi.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    ReportConverter reportConverter;

    public ReportDto create(ReportDto dto, Long postId, User currentUser) {
        Post post = postRepository.findById(postId).orElseThrow();
        if (post.getHidden().equals(true)){
            throw new RejectedOperationException(String.format("The post: %d, you are trying to report has already been treated", postId));
        }
        Report report = reportConverter.convert(dto,currentUser,post);
        report = reportRepository.save(report);

        return reportConverter.convert(report);
    }

    public ReportDto get(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();

        return reportConverter.convert(report);
    }

    public PagedResponse<ReportDto> getAll(int page, int size, boolean onlyVisible) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Report> reports;

        if (onlyVisible) {
            reports = reportRepository.findByPost_HiddenFalse(pageable);
        } else {
            reports = reportRepository.findAll(pageable);
        }

        if (reports.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        List<ReportDto> dtos = reportConverter.convert(reports.getContent());

        return new PagedResponse<>(dtos,reports.getNumber(),reports.getSize(), reports.getTotalElements(), reports.getTotalPages(), reports.isLast());
    }

    public Object getAllPostReports(Long postId, Integer page, Integer size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Post post = postRepository.findById(postId).orElseThrow();

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Report> reports = reportRepository.findAllByPost(post, pageable);

        if (reports.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        List<ReportDto> dtos = reportConverter.convert(reports.getContent());

        return new PagedResponse<>(dtos,reports.getNumber(),reports.getSize(), reports.getTotalElements(), reports.getTotalPages(), reports.isLast());
    }

}
