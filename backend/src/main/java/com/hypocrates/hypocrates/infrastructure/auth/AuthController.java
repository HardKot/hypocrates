package com.hypocrates.hypocrates.infrastructure.auth;

import com.hypocrates.hypocrates.infrastructure.auth.dto.ConfirmedCode;
import com.hypocrates.hypocrates.infrastructure.common.ConfirmedService;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.config.security.UserDetailsImpl;
import com.hypocrates.hypocrates.infrastructure.staff.StaffService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final StaffService staffService;
    private final ConfirmedService confirmedService;

    @GetMapping("/")
    public String login() {
        return "StaffLogin";
    }

    @GetMapping("/registration")
    public String registration() {
        return "StaffRegistration";
    }

    @PostMapping("/activate/staff")
    public ResponseEntity<StaffSchema> activateStaff(@RequestBody @Valid ConfirmedCode confirmedCode) {
        var codeID = UUID.fromString(confirmedCode.id());
        if (confirmedService.validateCode(codeID, confirmedCode.code())) {
            var staff = staffService.confirmedEmail(codeID, confirmedCode.code());
            return ResponseEntity.ok().body(staff);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/activate/staff/code/{id}")
    public ResponseEntity<StaffSchema> activateStaffCode(
            @PathVariable(name = "id") UUID id,
            @RequestParam(name = "code") String code,
            HttpServletResponse response
    ) throws IOException {
        if (confirmedService.confirmCode(id, code)) {
            response.sendRedirect("/");
            return null;
        }
        return ResponseEntity.badRequest().build();
    }



    private void staffAuth(StaffSchema staffSchema) {
        UserDetailsImpl.buildStaff(staffSchema);
    }
}
