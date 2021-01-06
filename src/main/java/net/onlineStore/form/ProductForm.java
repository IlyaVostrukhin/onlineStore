package net.onlineStore.form;

public class ProductForm {
    private Long idProduct;
    private Integer count;

    public ProductForm() {
    }

    public ProductForm(Long idProduct, Integer count) {
        this.idProduct = idProduct;
        this.count = count;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
