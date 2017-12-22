
export class Product{
    constructor(description,productType,price,quantity,brand){

        this.description = description;
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.id = null;
    }

    getDescription(){
        return this.description;
    }

    getProductType(){
        return this.productType;
    }

    getPrice(){
        return this.price;
    }

    getQuantity(){
        return this.quantity;
    }

    getBrand(){
        return this.brand;
    }

    setDescription(description){
        this.description = description;
    }

    setProductType(productType){
        this.productType = productType;
    }

    setPrice(price){
        this.price = price;
    }

    setQuantity(quantity){
        this.quantity = quantity;
    }

    setBrand(brand){
        this.brand =  brand;
    }

    getId(){
        return this.id;
    }

    setId(id){
        this.id = id;
    }
}