package tvz.nppjj.paris.init.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import tvz.nppjj.paris.model.exception.ParisException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ParisException("Missing or invalid Authorization header - Nemas to pravo!");
        }

        final String token = authHeader.substring(7); // The part after "Bearer "

        try {
            final Claims claims = Jwts.parser().setSigningKey(JwtSignature.KEY.getValue()).parseClaimsJws(token)
                    .getBody();
            request.setAttribute("claims", claims);
        } catch (final UnsupportedJwtException e) {
            throw new ServletException("Invalid token.");
        }

        chain.doFilter(req, res);
    }

}