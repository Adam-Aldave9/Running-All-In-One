    # Use the official Python base image
    FROM python:3.12-alpine
    COPY . /app
    # Set the working directory in the container
    WORKDIR /app

    # Install the Python dependencies
    RUN pip install --no-cache-dir -r requirements.txt
    # Set the environment variables
    ENV DJANGO_SETTINGS_MODULE=PartnerRequests.settings
    ENV PYTHONUNBUFFERED=1

    # Expose the port that Django runs on
    EXPOSE 8000

    # Run the Django development server
    CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]