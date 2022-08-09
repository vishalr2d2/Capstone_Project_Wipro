export class GlobalConstants {
    //Message
    public static genericError: string = "Something went wrong. Please try again later";

    public static productExistError: string = "Product already exists";

    public static productAdded: string = "Product added Successfully";

    public static unauthroized: string = "You are not authorized person to access this page";

    //Regex
    public static nameRegex: string = "[a-zA-Z0-9 ]*";

    public static emailRegex: string = "[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}";

    public static contactNumberRegex: string = "^[e0-9]{10,10}$";

    public static passwordRegex: string = "^(?=.?[A-Z])(?=.?[a-z])(?=.*?[0-9]).{6,15}$";

    //Variable
    public static error: string = "error";

}