package com.juliomesquita.application.domain.interfaces.controller;

import com.juliomesquita.application.domain.dto.EmployeeSearchDto;
import com.juliomesquita.application.domain.enums.SortType;
import com.juliomesquita.application.infra.utils.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Valid
@Tag(
        name = "Employee",
        description = "Employee Controller"
)
public interface SearchController {
    @Operation(
            operationId = "searchEmployees",
            summary = "Busca todos os colaboradores.",
            description = "Busca todos os colaboradores que se enquadram na pesquisa.",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Seccessful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponses.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request.", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {@Content(mediaType = "application/json")})
            }
    )
    @PostMapping(value = {"/search"}, produces = {"application/json"})
    default ResponseEntity<ApiResponses> searchEmployees(
            @RequestParam(name = "sort", defaultValue = "ASC") SortType sort,
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestBody EmployeeSearchDto employeeSearchDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(
            operationId = "findAllEmployees",
            summary = "Busca todos os colaboradores.",
            description = "Busca não paginada de todos os colaboradores.",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Seccessful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponses.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request.", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {@Content(mediaType = "application/json")})
            }
    )
    @GetMapping(produces = {"application/json"})
    default ResponseEntity<ApiResponses> findAllEmployees() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
