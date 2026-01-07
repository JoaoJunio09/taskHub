package com.joaojunio_dev.taskHub.controllers.docs;

import com.joaojunio_dev.taskHub.controllers.TaskController;
import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.model.enums.ThisDateOrPreviousOrLater;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TaskControllerDocs {

    @Operation(
        tags = {"Task"},
        summary = "Find's All Tasks",
        description = "Find's All Tasks",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON,
                array = @ArraySchema(schema = @Schema(implementation = TaskController.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<List<TaskDTO>> findAll();

    @Operation(
        tags = {"Task"},
        summary = "Find by one Task",
        description = "Find by one Task",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<TaskDTO> findById(Long id);

    @Operation(
        tags = {"Task"},
        summary = "Find one Task by Person Id",
        description = "Find one Task by Person Id",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<List<TaskDTO>> findByPersonId(Long personId);

    @Operation(
        tags = {"Task"},
        summary = "Find one Task by Completed",
        description = "Find one Task by Completed",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<List<TaskDTO>> findByCompleted(Boolean completed);

    @Operation(
        tags = {"Task"},
        summary = "Find one Task by Completed",
        description = "Find one Task by Completed",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<List<TaskDTO>> findByDate(LocalDate date, ThisDateOrPreviousOrLater type);

    @Operation(
        tags = {"Task"},
        summary = "Create a one Task",
        description = "Create a one Task",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<TaskDTO> create(TaskDTO task);

    @Operation(
        tags = {"Task"},
        summary = "Update a exists one Task",
        description = "Update a exists one Task",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<TaskDTO> update(TaskDTO task);

    @Operation(
        tags = {"Task"},
        summary = "Delete a exists one Task",
        description = "Delete a exists one Task",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "Success", content = @Content(
                mediaType = MediaTypes.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        }
    )
    ResponseEntity<?> delete(Long id);
}
