package com.pragma.hogar360_microservice_user.infraestructure.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.Claim;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.pragma.hogar360_microservice_user.infraestructure.exceptionshandler.ExceptionConstants.*;
import static com.pragma.hogar360_microservice_user.infraestructure.utils.constants.InfrastructureConstants.*;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String generateToken(UserModel userModel) {
        return JWT.create()
                .withSubject(userModel.getEmail())
                .withIssuer(userGenerator)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .withClaim(AUTHORITIES_CLAIM_KEY, ROLE_PREFIX.concat(userModel.getRoleModel().getName()))
                .sign(Algorithm.HMAC256(privateKey));
    }

    public DecodedJWT validateToken(String token){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(privateKey))
                    .withIssuer(userGenerator)
                    .build();

            return jwtVerifier.verify(token);
        } catch (AlgorithmMismatchException ex) {
            throw new JWTVerificationException(TOKEN_ALGORITHM_INVALID_MESSAGE);
        } catch (SignatureVerificationException ex) {
            throw new JWTVerificationException(TOKEN_SIGNATURE_INVALID_MESSAGE);
        } catch (TokenExpiredException ex) {
            throw new JWTVerificationException(TOKEN_EXPIRED_MESSAGE);
        } catch (InvalidClaimException ex) {
            throw new JWTVerificationException(TOKEN_ISSUER_INVALID_MESSAGE);
        } catch (JWTDecodeException ex) {
            throw new JWTVerificationException(TOKEN_MALFORMED_MESSAGE);
        } catch (IllegalArgumentException ex) {
            throw new JWTVerificationException(INVALID_KEY_OR_TOKEN_MESSAGE);
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String nameClaim) {
        return decodedJWT.getClaim(nameClaim);
    }
}
