package unimiskolc.java.hotel_booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.hotel_booking.dto.RoomDTO;
import unimiskolc.java.hotel_booking.model.Room;
import unimiskolc.java.hotel_booking.service.RoomService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "Add a new room")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoom(@RequestBody RoomDTO roomDTO) {
        Room room = convertToEntity(roomDTO);
        roomService.addRoom(room);
    }

    @Operation(summary = "Update an existing room")
    @PutMapping("/{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        Room room = convertToEntity(roomDTO);
        room.setId(id);
        roomService.updateRoom(room);
    }

    @Operation(summary = "Delete a room")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @Operation(summary = "Get a room by ID")
    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return convertToDTO(roomService.findRoomById(id));
    }

    @Operation(summary = "List all rooms")
    @GetMapping
    public List<RoomDTO> listRooms() {
        List<Room> rooms = roomService.listRooms();
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOs.add(convertToDTO(room));
        }
        return roomDTOs;
    }

    private Room convertToEntity(RoomDTO dto) {
        Room room = new Room();
        room.setId(dto.getId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setCapacity(dto.getCapacity());
        room.setPricePerNight(dto.getPricePerNight());
        return room;
    }

    private RoomDTO convertToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setCapacity(room.getCapacity());
        dto.setPricePerNight(room.getPricePerNight());
        return dto;
    }
}