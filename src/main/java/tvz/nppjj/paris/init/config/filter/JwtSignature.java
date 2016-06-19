package tvz.nppjj.paris.init.config.filter;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tvz.nppjj.paris.model.dto.UserDto;

public enum JwtSignature {

    KEY("p@jXrn0d*t._~:SVnTC*&G9$C#zQ6H+TttHF7D=-7");

    private String              key;

    private static final Logger LOG = LoggerFactory.getLogger(JwtSignature.class);

    private JwtSignature(String key) {
        this.key = key;
    }

    public String getValue() {
        return key;
    }

    public static String createJwtToken(UserDto userDto) {
        String jwtToken = Jwts.builder().setSubject(userDto.getEmail()).claim("roles", userDto.getRole())
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, KEY.getValue()).compact();
        LOG.info("Created new jwt signature: " + jwtToken);
        return jwtToken;
    }
}
