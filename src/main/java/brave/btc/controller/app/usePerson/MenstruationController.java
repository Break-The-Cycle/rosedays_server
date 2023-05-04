package brave.btc.controller.app.usePerson;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brave.btc.dto.CommonResponseDto;
import brave.btc.dto.app.menstruation.MenstruationDto;
import brave.btc.service.app.user.UsePersonAndMenstruationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "03-2. Use-person & menstruation", description = "앱 사용자 생리 관련 API")
@Slf4j
@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/use-persons/{usePersonId}/menstruation")
public class MenstruationController {

	private final UsePersonAndMenstruationService usePersonAndMenstruationService;


	@Operation(summary = "생리 기록 가져오기", description = "생리한 기록을 정보 가져오기",
		responses = {
			@ApiResponse(responseCode = "200", description = "생리 기록 조회 성공"),
			@ApiResponse(responseCode = "400", description = "조회 날짜 이상 / 회원 정보 불일치")
		})
	@GetMapping
	public ResponseEntity<?> findUsePersonRecordMenstruationList(
		@Parameter(description = "use person pk",required = true) @PathVariable("usePersonId") Integer usePersonId,
		@Parameter(description = "조회 시작 날짜",required = true) @RequestParam(name = "fromDate") LocalDate fromDate,
		@Parameter(description = "조회 종료 날짜",required = true) @RequestParam(name = "toDate") LocalDate toDate) {

		log.debug("[findUsePersonRecordMenstruationList] usePersonId: {} fromDate: {} toDate: {}",usePersonId, fromDate, toDate);

		List<MenstruationDto.Response> menstruationList = usePersonAndMenstruationService.findMenstruationList(usePersonId, fromDate, toDate);
		return ResponseEntity.ok()
			.body(menstruationList);
	}

	@Operation(summary = "생리 기록 등록", description = "생리한 기록을 등록",
		responses = {
			@ApiResponse(responseCode = "200", description = "생리 기록 성공"),
			@ApiResponse(responseCode = "400", description = "생리 날짜 이상 / 회원 정보 불일치 ")
		})
	@PostMapping
	public ResponseEntity<?> createUsePersonRecordMenstruation(
		@Parameter(description = "use person pk",required = true) @PathVariable("usePersonId") Integer usePersonId,
		@RequestBody MenstruationDto.Create mnsttCreateDto) {

		log.debug("[createUsePersonRecordMenstruation] usePersonId: {} mnsttCreateDto: {}",usePersonId, mnsttCreateDto);
		CommonResponseDto<?> responseDto = usePersonAndMenstruationService.createMenstruationInfo(usePersonId, mnsttCreateDto);

		return ResponseEntity.ok()
			.body(responseDto);
	}




}
