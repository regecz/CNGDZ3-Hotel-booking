package unimiskolc.java.hotel_booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.hotel_booking.dto.ServiceDTO;
import unimiskolc.java.hotel_booking.model.HotelService;
import unimiskolc.java.hotel_booking.service.HotelServiceService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
@Tag(name = "Service Controller", description = "Service management endpoints")
public class HotelServiceController {

    private final HotelServiceService hotelServiceService;

    public HotelServiceController(HotelServiceService hotelServiceService) {
        this.hotelServiceService = hotelServiceService;
    }

    @Operation(summary = "Add a new service")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addService(@RequestBody ServiceDTO hotelServiceDTO) {
        HotelService hotelService = convertToEntity(hotelServiceDTO);
        hotelServiceService.addService(hotelService);
    }

    @Operation(summary = "Update an existing service")
    @PutMapping("/{id}")
    public void updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        HotelService hotelService = convertToEntity(serviceDTO);
        hotelService.setId(id);
        hotelServiceService.updateService(hotelService);
    }

    @Operation(summary = "Delete a service")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable Long id) {
        hotelServiceService.deleteService(id);
    }

    @Operation(summary = "Get a service by ID")
    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable Long id) {
        return convertToDTO(hotelServiceService.findServiceById(id));
    }

    @Operation(summary = "List all services")
    @GetMapping
    public List<ServiceDTO> listServices() {
        List<HotelService> services = hotelServiceService.listServices();
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        for (HotelService service : services) {
            serviceDTOs.add(convertToDTO(service));
        }
        return serviceDTOs;
    }

    private HotelService convertToEntity(ServiceDTO dto) {
        HotelService hotelService = new HotelService();
        hotelService.setId(dto.getId());
        hotelService.setName(dto.getName());
        hotelService.setDescription(dto.getDescription());
        hotelService.setPrice(dto.getPrice());
        return hotelService;
    }

    private ServiceDTO convertToDTO(HotelService hotelService) {
        ServiceDTO dto = new ServiceDTO();
        dto.setId(hotelService.getId());
        dto.setName(hotelService.getName());
        dto.setDescription(hotelService.getDescription());
        dto.setPrice(hotelService.getPrice());
        return dto;
    }
}