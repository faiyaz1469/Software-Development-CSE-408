package com.zahid.seeder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.zahid.models.Account;
import com.zahid.models.Role;
import com.zahid.services.AccountService;
import com.zahid.services.RoleService;

@Component
public class AccountSeeder implements CommandLineRunner {

    private static String DATA_URL = "https://raw.githubusercontent.com/digital-animal/xplorebd-files/main/accounts.csv";
    private List<Account> allData = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(AccountSeeder.class);
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    // loadingb seeded data from csv file hosted in github
    public void loadSeedData() throws IOException, InterruptedException {
        List<Account> newData = new ArrayList<>(); // concurrency issue resolving
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(response.body());
        
        // StringReader csvBodyReader = new StringReader(response.body());
        // CSVFormat format = CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true).build();
        // CSVParser csvParser = new CSVParser(csvBodyReader, format);
        // List<CSVRecord> records = csvParser.getRecords();
        
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        List<Account> accounts = accountService.getAllAccounts();
        
        if(accounts.size() == 0) {

            Role user = new Role("ROLE_USER");
            Role admin = new Role("ROLE_ADMIN");

            roleService.addRole(user);
            roleService.addRole(admin);

            Set<Role> minRoles = new HashSet<>();
            minRoles.add(user);
            Set<Role> maxRoles = new HashSet<>();
            maxRoles.add(user);
            maxRoles.add(admin);

            int n = 0;

            for (CSVRecord record : records) {
                // Province/State,Country/Region,Lat,Long
                String email = record.get("Email");
                String password = record.get("Password");
                String firstName = record.get("First Name");
                String lastName = record.get("Last Name");
                Account account = new Account(email, password, firstName, lastName);
                
                if (n % 2 == 0) {
                    // account.setRoles(minRoles);
                    account.setRole(user);
                } else {
                    account.setRole(admin);
                }
                accountService.addAccount(account);

                n++;
 
            }
            
            logger.info("Number of accounts : {}", accountService.getAllAccounts().size());

        }

    }

}
