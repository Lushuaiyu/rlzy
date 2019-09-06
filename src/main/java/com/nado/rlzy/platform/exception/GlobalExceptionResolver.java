package com.nado.rlzy.platform.exception;

/**
 * 异常处理类
 * @ClassName
 * @Description TODO
 * @Author ar
 * @Data 2019/5/20 19:03
 * @Version 1.0
 */

//@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionResolver {
   /* @Autowired
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    *//**
     * 请求类型参数错误异常的捕获
     *
     * @return com.b2b.b2bmanage.bean.model.ResponseBean<java.lang.String>
     * @Author ar
     * @Description
     * @Date 19:08 2019/5/20
     * @Param * @param e
     **//*
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseBean<String> badRequest(BindException e) {
        logger.error("occurs error when execute method1", e);
        return new ResponseBean<>(false, UnicomResponseEnums.BAD_REQUEST);
    }

    *//**
     * 404错误异常的捕获
     *
     * @param e
     * @return
     *//*
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseBean<String> badRequestNotFound(BindException e) {
        logger.error("occurs error when execute method2", e);
        return new ResponseBean<>(false, null, UnicomResponseEnums.NOT_FOUND);
    }

    *//**
     * mybatis未绑定异常
     *
     * @param e
     * @return
     *//*
    @ExceptionHandler(BindingException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<String> mybatis(Exception e) {
        logger.error("occurs error when execute method3", e);
        return new ResponseBean<>(false, UnicomResponseEnums.BOUND_STATEMENT_NOT_FOUNT);
    }

    *//**
     * 自定义异常的捕获
     * 自定义抛出异常。统一的在这里捕获返回JSON格式的友好提示。
     *
     * @param exception
     * @param request
     * @return
     *//*
    @ExceptionHandler(value = {AssertException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultJson sendError(AssertException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        logger.error("occurs error when execute url ={}4", requestURI, exception);
        ResultJson resultJson = new ResultJson();

        logger.error("occurs error when execute method7", exception);
        resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
        resultJson.setMessage(exception.getMessage());
        return resultJson;

    }


    *//**
     * 数据库操作出现异常
     *
     * @param e
     * @return
     *//*
    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<String> systemError(Exception e) {
        logger.error("occurs error when execute method5", e);
        return new ResponseBean<>(false, UnicomResponseEnums.DATABASE_ERROR);
    }

    *//**
     * 网络连接失败！
     *
     * @param e
     * @return
     *//*
    @ExceptionHandler(value = {ConnectException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<String> connect(Exception e) {
        logger.error("occurs error when execute method6", e);
        return new ResponseBean<>(false, UnicomResponseEnums.CONNECTION_ERROR);
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseBean<String> notAllowed(Exception e) {
        logger.error("occurs error when execute method7", e);
        return new ResponseBean<>(false, UnicomResponseEnums.METHOD_NOT_ALLOWED);
    }*/

  /*  @ExceptionHandler(value = {AssertException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ResultJson assertException(AssertException e1) {
        System.out.println(e1);
        ResultJson resultJson = new ResultJson();

        logger.error("occurs error when execute method7", e1);
       resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
        resultJson.setMessage(e1.getMessage());
        return resultJson;
        //return new AssertException(e.getMsg());
    }*/


}