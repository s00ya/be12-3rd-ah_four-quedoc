package com.example.admin.service;

import com.example.admin.model.User;
import com.example.core.common.CustomException;
import com.example.core.common.ErrorCode;
import com.example.admin.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;

    public void save(@Valid User user) {
        try {
            logger.info("save user");
            userRepository.save(user);
        }catch (DataIntegrityViolationException e) {  // 무결성 위반 (중복, NULL 등)
            logger.error("DataIntegrityViolationException");
            throw new CustomException(ErrorCode.INTEGRITY_CONSTRAINT_VIOLATION);
        } catch (ConstraintViolationException e) {  // Hibernate Validator 위반
            logger.error("ConstraintViolationException");
            throw new CustomException(ErrorCode.VERIFICATION_FAILED);
        } catch (Exception e) {  // 기타 예외
            logger.error("Exception");
            throw new CustomException(ErrorCode.SAVE_FAILED);
        }
    }

    public User findByEmail(String email) {
        Optional<User> optionalUser = null;
        try {
            logger.info("findByEmail");
            optionalUser = userRepository.findByEmail(email);
            if(!optionalUser.isPresent()) {
                throw new CustomException(ErrorCode.NO_EXIST);
            }
        } catch (DataAccessException e) {   // 데이터베이스 관련 예외
            logger.error("DataAccessException");
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        } catch (IllegalArgumentException e) {  // email 값이 이상한 예외
            logger.error("IllegalArgumentException");
            throw new CustomException(ErrorCode.ILLEGAL_ARGUMENT_EMAIL);
        } catch (Exception e) {
            logger.error("Exception");
            throw new CustomException(ErrorCode.SAVE_FAILED);
        }
        return optionalUser.get();
    }
    public User findById(Long id) {
        Optional<User> optionalUser = null;
        try {
            logger.info("findById");
            optionalUser = userRepository.findById(id);
            if(!optionalUser.isPresent()) {
                throw new CustomException(ErrorCode.NO_EXIST);
            }
        } catch (DataAccessException e) {   // 데이터베이스 관련 예외
            logger.error("DataAccessException");
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        } catch (IllegalArgumentException e) {  // id 값이 이상한 예외
            logger.error("IllegalArgumentException");
            throw new CustomException(ErrorCode.ILLEGAL_ARGUMENT_ID);
        } catch (Exception e) {
            logger.error("Exception");
            throw new CustomException(ErrorCode.SAVE_FAILED);
        }
        return optionalUser.get();
    }

    public void withdrawal(User user) {
        try {
            userRepository.delete(user);
        } catch (DataAccessException e) {
            logger.error("DataAccessException");
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException");
            throw new CustomException(ErrorCode.ILLEGAL_ARGUMENT_ID);
        } catch (Exception e) {
            logger.error("Exception");
            throw new CustomException(ErrorCode.DELETE_FAIL);
        }
    }


}
