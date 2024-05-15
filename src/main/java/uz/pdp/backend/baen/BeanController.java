    package uz.pdp.backend.baen;

    import uz.pdp.backend.maker.MessageMaker;
    import uz.pdp.backend.service.bookService.BadiyService;
    import uz.pdp.backend.service.bookService.FantastikService;
    import uz.pdp.backend.service.bookService.RomantikService;
    import uz.pdp.backend.service.userService.UserService;
    import uz.pdp.backend.service.userService.UserServiceImp;

    public interface BeanController {
    ThreadLocal<UserService> userServiceByThreadLocal = ThreadLocal.withInitial(UserServiceImp::new);
    ThreadLocal<BadiyService> badiyServiceByThreadLocal = ThreadLocal.withInitial(BadiyService::new);
    ThreadLocal<RomantikService> romantikServiceByThreadLocal = ThreadLocal.withInitial(RomantikService::new);
    ThreadLocal<FantastikService> fantastikServiceByThreadLocal = ThreadLocal.withInitial(FantastikService::new);
    ThreadLocal<MessageMaker> messageMakerByThreadLocal = ThreadLocal.withInitial(MessageMaker::new);

}
