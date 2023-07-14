package my.garden.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.garden.dto.BoardQnADTO;

@Component
@Aspect
public class WriterAdviser {
  @Autowired
  private HttpSession session;

  @Pointcut("execution(* my.garden.controller.BoardReviewAndQnAController.reviewWriteForm(..))")
  public void reviewWriteForm() {
  }

  @Pointcut("execution(* my.garden.controller.BoardReviewAndQnAController.writeReview(..))")
  public void writeReview() {
  }

  @Pointcut("execution(* my.garden.controller.BoardReviewAndQnAController.reviewUpdateForm(..))")
  public void reviewUpdateForm() {
  }

  @Pointcut("execution(* my.garden.controller.BoardReviewAndQnAController.reviewUpdate(..))")
  public void reviewUpdate() {
  }

  @Around("reviewWriteForm() ||  writeReview() || reviewUpdateForm() || reviewUpdate()")
  public Object onlyForCompletedProducts(ProceedingJoinPoint pjp) throws Throwable { //배송완료 상품만 후기작성 가능하도록 처리
    int pnumber = (int) session.getAttribute("pnumber");
    if (session.getAttribute("myCompletedPNum") == "pnumber") {
      return pjp.proceed();
    } else {
      return "redirect:productsRead?&revPage=1&qnaPage=1&pnumber=" + pnumber;
    }
  }

}
