// Copyright 2015 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package adwords.axis.v201509.basicoperations;

import com.google.api.ads.adwords.axis.factory.AdWordsServices;
import com.google.api.ads.adwords.axis.v201509.cm.AdServingOptimizationStatus;
import com.google.api.ads.adwords.axis.v201509.cm.AdvertisingChannelType;
import com.google.api.ads.adwords.axis.v201509.cm.BiddingStrategyConfiguration;
import com.google.api.ads.adwords.axis.v201509.cm.BiddingStrategyType;
import com.google.api.ads.adwords.axis.v201509.cm.Budget;
import com.google.api.ads.adwords.axis.v201509.cm.BudgetBudgetDeliveryMethod;
import com.google.api.ads.adwords.axis.v201509.cm.BudgetBudgetPeriod;
import com.google.api.ads.adwords.axis.v201509.cm.BudgetOperation;
import com.google.api.ads.adwords.axis.v201509.cm.BudgetServiceInterface;
import com.google.api.ads.adwords.axis.v201509.cm.Campaign;
import com.google.api.ads.adwords.axis.v201509.cm.CampaignOperation;
import com.google.api.ads.adwords.axis.v201509.cm.CampaignReturnValue;
import com.google.api.ads.adwords.axis.v201509.cm.CampaignServiceInterface;
import com.google.api.ads.adwords.axis.v201509.cm.CampaignStatus;
import com.google.api.ads.adwords.axis.v201509.cm.FrequencyCap;
import com.google.api.ads.adwords.axis.v201509.cm.Level;
import com.google.api.ads.adwords.axis.v201509.cm.Money;
import com.google.api.ads.adwords.axis.v201509.cm.NetworkSetting;
import com.google.api.ads.adwords.axis.v201509.cm.Operator;
import com.google.api.ads.adwords.axis.v201509.cm.RealTimeBiddingSetting;
import com.google.api.ads.adwords.axis.v201509.cm.Setting;
import com.google.api.ads.adwords.axis.v201509.cm.TimeUnit;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.client.auth.oauth2.Credential;

import org.joda.time.DateTime;

/**
 * This example adds campaigns with RealTimeBidding enabled.
 *
 * Credentials and properties in {@code fromFile()} are pulled from the
 * "ads.properties" file. See README for more info.
 */
public class AddRTBCampaigns {

  public static void main(String[] args) throws Exception {
    // Generate a refreshable OAuth2 credential similar to a ClientLogin token
    // and can be used in place of a service account.
    Credential oAuth2Credential = new OfflineCredentials.Builder()
        .forApi(Api.ADWORDS)
        .fromFile()
        .build()
        .generateCredential();

    // Construct an AdWordsSession.
    AdWordsSession session = new AdWordsSession.Builder()
        .fromFile()
        .withOAuth2Credential(oAuth2Credential)
        .build();

    AdWordsServices adWordsServices = new AdWordsServices();

    runExample(adWordsServices, session);
  }

  public static void runExample(
      AdWordsServices adWordsServices, AdWordsSession session) throws Exception {
    // Get the BudgetService.
    BudgetServiceInterface budgetService =
        adWordsServices.get(session, BudgetServiceInterface.class);

    // Create a budget, which can be shared by multiple campaigns.
    Budget sharedBudget = new Budget();
    sharedBudget.setName("Interplanetary Cruise #" + System.currentTimeMillis());
    Money budgetAmount = new Money();
    budgetAmount.setMicroAmount(50000000L);
    sharedBudget.setAmount(budgetAmount);
    sharedBudget.setDeliveryMethod(BudgetBudgetDeliveryMethod.STANDARD);
    sharedBudget.setPeriod(BudgetBudgetPeriod.DAILY);

    BudgetOperation budgetOperation = new BudgetOperation();
    budgetOperation.setOperand(sharedBudget);
    budgetOperation.setOperator(Operator.ADD);

    // Add the budget
    Long budgetId =
        budgetService.mutate(new BudgetOperation[] {budgetOperation}).getValue(0).getBudgetId();

    // Get the CampaignService.
    CampaignServiceInterface campaignService =
        adWordsServices.get(session, CampaignServiceInterface.class);

    // Create campaign.
    Campaign campaign = new Campaign();
    campaign.setName("Interplanetary Cruise #" + System.currentTimeMillis());
    campaign.setStatus(CampaignStatus.PAUSED);
    BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
    biddingStrategyConfiguration.setBiddingStrategyType(BiddingStrategyType.MANUAL_CPC);
    campaign.setBiddingStrategyConfiguration(biddingStrategyConfiguration);

    // You can optionally provide these field(s).
    campaign.setStartDate(new DateTime().plusDays(1).toString("yyyyMMdd"));
    campaign.setStartDate(new DateTime().plusDays(30).toString("yyyyMMdd"));
    campaign.setAdServingOptimizationStatus(AdServingOptimizationStatus.ROTATE);
    campaign.setFrequencyCap(new FrequencyCap(5L, TimeUnit.DAY, Level.ADGROUP));

    // Create budget.
    Budget budget = new Budget();
    budget.setBudgetId(budgetId);
    campaign.setBudget(budget);

    // Set the campaign network options to Content Network.
    NetworkSetting networkSetting = new NetworkSetting();
    networkSetting.setTargetContentNetwork(true);
    campaign.setNetworkSetting(networkSetting);
    campaign.setAdvertisingChannelType(AdvertisingChannelType.DISPLAY);

    // Make the Campaign an RTB Campaign.
    campaign.setSettings(new Setting[] {new RealTimeBiddingSetting(null, true)});

    // You can create multiple campaigns in a single request.
    Campaign campaign2 = new Campaign();
    campaign2.setName("Interplanetary Cruise banner #" + System.currentTimeMillis());
    campaign2.setStatus(CampaignStatus.PAUSED);
    BiddingStrategyConfiguration biddingStrategyConfiguration2 = new BiddingStrategyConfiguration();
    biddingStrategyConfiguration2.setBiddingStrategyType(BiddingStrategyType.MANUAL_CPC);
    campaign2.setBiddingStrategyConfiguration(biddingStrategyConfiguration2);

    Budget budget2 = new Budget();
    budget2.setBudgetId(budgetId);
    campaign2.setBudget(budget2);

    NetworkSetting networkSetting2 = new NetworkSetting();
    networkSetting2.setTargetContentNetwork(true);
    campaign2.setNetworkSetting(networkSetting2);
    campaign2.setAdvertisingChannelType(AdvertisingChannelType.DISPLAY);

    campaign2.setSettings(new Setting[] {new RealTimeBiddingSetting(null, true)});

    // Create operations.
    CampaignOperation operation = new CampaignOperation();
    operation.setOperand(campaign);
    operation.setOperator(Operator.ADD);
    CampaignOperation operation2 = new CampaignOperation();
    operation2.setOperand(campaign2);
    operation2.setOperator(Operator.ADD);

    CampaignOperation[] operations = new CampaignOperation[] {operation, operation2};

    // Add campaigns.
    CampaignReturnValue result = campaignService.mutate(operations);

    // Display campaigns.
    for (Campaign campaignResult : result.getValue()) {
      System.out.println("Campaign with name \"" + campaignResult.getName() + "\" and id \""
          + campaignResult.getId() + "\" was added.");
    }
  }
}
