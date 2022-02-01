// Move to admin db
db = db.getSiblingDB('admin');
// Authenticate as root
db.auth("admin", "admin");
// Create and move subscription db
db = db.getSiblingDB('subscription');
// Create subscriptions collection
db.createCollection('subscriptions');
// Create and move to mail db
db = db.getSiblingDB('mail');
// Create notification mails collection
db.createCollection('notifications');
// Insert sample notification mail
db.notifications.insert([
  {
    "subject": "Welcome to Adidas newsletter",
    "text": "Hi {subscriber} and welcome to the Adidas newsletter",
    "campaign": "adidas-campaign"
  },
  {
    "subject": "Welcome!",
    "text": "Hi {subscriber}, you have been successfully registered.",
    "campaign": "default"
  },
]);
