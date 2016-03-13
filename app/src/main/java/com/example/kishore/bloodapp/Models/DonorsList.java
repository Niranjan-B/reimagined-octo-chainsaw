package com.example.kishore.bloodapp.Models;

import java.util.ArrayList;

/**
 * Created by NIRANJAN on 13-03-2016.
 */
public class DonorsList {

    private Boolean status;
    private ArrayList<DonorsCredentials> donors = new ArrayList<>();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<DonorsCredentials> getDonors() {
        return donors;
    }

    public void setDonors(ArrayList<DonorsCredentials> donors) {
        this.donors = donors;
    }
}
