@login
@n11Favorite
@close
  Feature: login to n11
    Scenario:
      Given user goes to homepage
      Then user on the homepage "https://www.n11.com/"

      When user click the login button
      Then user should see "Giriş Yap"
      And user enters email and password and click login button
      Then user should see "Siparişlerim" ont the account menu

      When user search the  "Iphone" word
      Then user should on the "Iphone" result page
      And user goes the "2". page
      And user add the number "3" product to favorites
      And user goes to account page and click the İstek Listem-Favorilerim
      Then user see the added product in favorites page

      When user delete the favorite product from favorites page
      Then user should not see the same favorite product on the favorite page
      And user logs out

