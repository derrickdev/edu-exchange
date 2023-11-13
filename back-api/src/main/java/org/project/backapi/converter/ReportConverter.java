package org.project.backapi.converter;

import org.modelmapper.ModelMapper;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.Report;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.ReportDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportConverter {
    public ReportDto convert(Report report) {
        ReportDto dto = new ReportDto();
        dto.setId(report.getId());
        dto.setPostId(report.getPost().getId());
        dto.setReporterId(report.getReporter().getId());
        dto.setReason(report.getReason());
        dto.setCreatedAt(report.getCreatedAt());

        return dto;
    }

    public List<ReportDto> convert(List<Report> reports) {
        ModelMapper modelMapper = new ModelMapper();
        List<ReportDto> converted = new ArrayList<>();
        for (Report report : reports) {
            converted.add(convert(report));
        }

        return converted;
    }

    public Report convert(ReportDto dto, User user, Post post) {
        Report report = new Report();
        report.setReason(dto.getReason());
        report.setReporter(user);
        report.setPost(post);

        return report;
    }
}
