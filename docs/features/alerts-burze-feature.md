## Burze.dzis.net feature

### Story: 
As a user, I want to see meteo alerts so that I will be informed about the newest warnings

### Acceptance Criteria:

- User can trigger sync on demand
- Synchronize the latest changes from `burze.dzis.net` to database
- Display 5 meteo alerts per page

### Story: 
Application needs to fetch changes every 10 minutes

### Acceptance Criteria:

- Application has working scheduler that synchronize all changes every 10 minutes
- Changes are saved inside database
- Application retries synchronization 3 times when service is not available

