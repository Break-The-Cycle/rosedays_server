package brave.btc.domain.bo.user;

import java.time.LocalTime;

import org.hibernate.annotations.Comment;

import brave.btc.constant.enums.OfficialInstitutionDivision;
import brave.btc.domain.bo.Address;
import brave.btc.dto.bo.OfficialInstitutionDto;
import brave.btc.util.converter.OfficialInstitutionDivisionToCodeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OFFICIAL_INSTITUTION")
public class OfficialInstitution {

	@Comment("공식 기관 ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFICIAL_INSTT_ID", columnDefinition = "INT NOT NULL")
	private Integer id;

	@Comment("공식 기관 이름")
	@Column(name = "OFFICIAL_INSTT_NAME", columnDefinition = "VARCHAR(45) NOT NULL UNIQUE", nullable = false)
	private String name;

	@Comment("공식 기관 전화 번호")
	@Column(name = "OFFICIAL_INSTT_PNBR", columnDefinition = "VARCHAR(18) NOT NULL UNIQUE", nullable = false)
	private String phoneNumber;

	@Convert(converter = OfficialInstitutionDivisionToCodeConverter.class)
	@Comment("공식 기관 코드")
	@Column(name = "OFFICIAL_INSTT_CODE", columnDefinition = "VARCHAR(3) NOT NULL", nullable = false)
	private OfficialInstitutionDivision code;

	@Comment("기관 운영 시작 시간")
	@Column(name = "INSTT_OPRTN_START_TIME", nullable = true)
	private LocalTime startTime;

	@Comment("기관 운영 종료 시간")
	@Column(name = "INSTT_OPRTN_END_TIME", nullable = true)
	private LocalTime endTime;

	@Comment("공식 기관 주소")
	@JoinColumn(name = "ADDRESS_ID", columnDefinition = "INT NOT NULL", nullable = false)
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	@ToString.Exclude
	private Address address;

	public OfficialInstitutionDto.Response toResponseDto() {
		return OfficialInstitutionDto.Response.builder()
			.id(id)
			.name(name)
			.phoneNumber(phoneNumber)
			.code(code)
			.startTime(startTime)
			.endTime(endTime)
			.build();
	}

}