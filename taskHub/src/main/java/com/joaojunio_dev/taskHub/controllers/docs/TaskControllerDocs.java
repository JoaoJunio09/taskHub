package com.joaojunio_dev.taskHub.controllers.docs;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TaskControllerDocs {

    @Operation(tags = {"Task"})
    ResponseEntity<List<TaskDTO>> findAll();

    @Operation(tags = {"Task"})
    ResponseEntity<TaskDTO> findById(@PathVariable Long id);

    @Operation(tags = {"Task"})
    ResponseEntity<TaskDTO> create(@RequestBody TaskDTO task);

    @Operation(tags = {"Task"})
    ResponseEntity<TaskDTO> update(@RequestBody TaskDTO task);

    @Operation(tags = {"Task"})
    ResponseEntity<?> delete(@PathVariable Long id);
}
