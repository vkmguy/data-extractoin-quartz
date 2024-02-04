# Stock Market Quartz Scheduler App

## Prerequisites

Before you begin, make sure you have the following installed on your machine:

- Docker: [Install Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Install Docker Compose](https://docs.docker.com/compose/install/)

## Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/vkmguy/data-extractoin-quartz.git
    cd data-extractoin-quartz
    ```

2. **Build and start the services:**

    ```bash
    docker-compose up --build
    ```

   This command will build the Docker images and start the services.

   1. **Access your application:**
      - Web App: [http://localhost:8090/api](http://localhost:8090/api)
      - POST request to create a job: [http://localhost:8090/api/quartz/groups/:group/jobs](http://localhost:8090/api/quartz/groups/:group/jobs)
      - param : group=Group 1
      ```json
      {
      "name": "Job For Train Test Split",
      "data": {
      "StoredValueOne": "Train Test Split Stragety"
      },
      "triggers": [
      {
      "name": "Train Test Split Stragety",
      "group": "Group 1",
      "cron": "0 0 * * * ?"
      }
      ]
      }
      ```
      - PATCH request to trigger the job:[http://localhost:8090/api/quartz/groups/:group/jobs/:name/trigger](http://localhost:8090/api/quartz/groups/:group/jobs/:name/trigger)
      - param: group=Group 1
      - param: name=Job For Train Test Split
5. **To stop the services, press `Ctrl + C` in the terminal where `docker-compose` is running.**

## Services

### Quartz App

The quartz app is used to schedule data extraction jobs from the YFinance API. Visit [http://localhost:8090/api](http://localhost:8090/api) to access the APIs.

### PostgreSQL Database

The PostgreSQL database stores jobs and triggers. You can access the database using tools like `pgAdmin` or connect directly to the database container.

## Troubleshooting

- If you encounter issues, check the logs of individual containers for more details.

## License

This project is licensed under the [MIT License](LICENSE).
