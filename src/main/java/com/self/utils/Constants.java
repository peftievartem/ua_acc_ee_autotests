package com.self.utils;

import com.self.utils.helpers.FileReaderManager;


public class Constants {
    //region Configs
    public static final String DATABASE_NAME = FileReaderManager.getInstance().getConfigReader().getDatabaseName();
    public static final String USER_LOGIN_SUPER_ADMIN = FileReaderManager.getInstance().getConfigReader().getUserNameSuperAdmin();
    public static final String USER_LOGIN_LOCAL_ADMIN = FileReaderManager.getInstance().getConfigReader().getUserNameLocalAdmin();
    public static final String USER_PASSWORD = FileReaderManager.getInstance().getConfigReader().getPassword();
    public static final String BASE_URL= FileReaderManager.getInstance().getConfigReader().getApplicationUrl();


    public static final int EXPLICIT_WAIT_TIMEOUT = FileReaderManager.getInstance().getConfigReader().getExplicitWaitSec();
    public static final int AJAX_TIMEOUT = FileReaderManager.getInstance().getConfigReader().getAjaxTimeout();
    public static final int RETRY_TIMEOUT = FileReaderManager.getInstance().getConfigReader().getRetryTimeout();
    //endregion

    //region REGEX CONSTANTS
    public static final String NUMBERS_FROM_ZERO_TO_NINE_AND_DOT = "[^0-9.]";
    public static final String NUMBERS_FROM_ZERO_TO_NINE_AND_COMMA = "[^0-9,]";
    public static final String NUMBERS_FROM_ZERO_TO_NINE_DOT_AND_COMMA = "[^0-9.,]";
    public static final String ALL_CHARS_SPACE = "[0-9]";
    public static final Character COMMA_SYMBOL = ',';
    public static final Character DOT_SYMBOL = '.';
    public static final String VOID_REPLACEMENT = "";
    public static final String SPACE_SYMBOL = " ";
    public static final String LETTERS_SPACE = "[^A-Za-z ]*";
    //endregion

    // region RANDOM NUMBERS
    public static long RANDOM_NUM = (long) (Math.random()*Math.pow(10,10));
    //endregion

    //region Test Data
    public static final String constantsDescriptionName = "AutoTest description";
    public static final String constantsReferenceName = "Autotest reference";
    public static final String constantsStartDate = "01012022";
    public static final String constantsCompanyCurrency = "USD";
    public static final String constantsOtherCurrency = "UAH";
    public static final String constantsSalaryStructure = "Timesheet 903";
    public static final String constantsSalaryJournal = "Salary accrual";
    public static final String constantsDebitAccountName = "131";
    public static final String constantsCreditAccountName = "903";
    public static final String constantsJournalPaymentName = "Mesh cards";
    public static final String constantsJournalSaleName = "Рахунки клієнтам";
    public static final String constantsAssetType = "Laptops";
    public static final String constantsPeriodName = "2022 Period #1";
    //endregion

}
