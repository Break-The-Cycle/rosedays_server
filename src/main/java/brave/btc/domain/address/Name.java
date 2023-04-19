package brave.btc.domain.address;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "ADDRESS_NAME")
public class Name {


	@Comment("지역 ID")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID", columnDefinition = "INT NOT NULL")
	private Integer id;

	@Comment("지역 구분")
	@Column(name = "ADDRESS_DVSN", columnDefinition = "VARCHAR(100) NOT NULL")
	private String name;
}