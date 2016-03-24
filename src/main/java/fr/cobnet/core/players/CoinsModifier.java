package fr.cobnet.core.players;

import org.apache.commons.lang.Validate;

/**
 * Créé par Creart le 01/01/2016.
 *
 * @apiNote > Ne <bold>jamais</bold> faire perdre des coins à un joueur, la quantité négative sert seulement pour les achats
 */
public class CoinsModifier {

    private String reason;
    private boolean positive;
    private int amount;
    private Currency currency;

    /**
     *
     * @param reason > Raison de l'ajout / suppression de coins
     * @param amount > Quantité de monaie à ajouter
     * @param currency > Type de monnaie
     */
    public CoinsModifier(String reason, int amount, Currency currency) {
        Validate.notNull(reason);
        Validate.isTrue(amount != 0);
        Validate.notNull(currency);

        this.reason = reason;
        this.positive = amount > 0;
        this.amount = amount;
        this.currency = currency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isPositive() {
        return positive;
    }

}
