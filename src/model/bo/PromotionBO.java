package model.bo;

import model.beans.Promotion;
import model.dao.PromotionDAO;

public class PromotionBO {
	PromotionDAO promotionDAO = new PromotionDAO();

	public Promotion getPromotionByCode(String promotionCodeForm) {
		return promotionDAO.getPromotionByCode(promotionCodeForm);
	}

}
