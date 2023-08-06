package k15.k15dcpm03.demo.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import k15.k15dcpm03.demo.user.CustomUserDetails;

@Component
public class JwtTokenProvider {
    private final String key = "dhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgiadhgia";
    //Mã bí mật để ký JWT (cần được bảo mật)
    private final long JWT_EXPIRATION = 604800000L;
    //Thời gian hết hạn của JWT 
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    //Hàm tạo JWT
    @SuppressWarnings("deprecation")
	public String generateToken(CustomUserDetails userDetails) {
        
    	// Lấy thông tin user
    	// Thời gian hiện tại
        Date now = new Date();
        // Thời gian hết hạn
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        String jwt="";
        try {
         // Tạo JWT
         jwt=Jwts.builder()
        		// Thông tin chủ đề (ví dụ: userId)
                .setSubject(Long.toString(userDetails.getUser().getId()))
                // Thời gian phát hành
                .setIssuedAt(now)
                // Thời gian hết hạn
                .setExpiration(expiryDate)
                // Ký JWT bằng thuật toán HS256 và mã bí mật
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        }catch(Exception ex)
        {
        	System.out.println("Lỗi xảy ra:"+ex.getMessage());
        }
        // Tạo chuỗi json web token từ id của user.
        return jwt;
    }
    
    
    
    
    // Lấy thông tin người dùng từ JWT
    public Long getUserIdFromJWT(String token) {
    	// Giải mã JWT và xác thực
        @SuppressWarnings("deprecation")
		Claims claims = Jwts.parser()
                            .setSigningKey(key)
                            .parseClaimsJws(token)
                            .getBody();

        return Long.parseLong(claims.getSubject());
    }

    //Kiểm tra token
    @SuppressWarnings("deprecation")
	public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
           
        	logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
