CREATE TABLE payments (
	payment_id uuid NOT NULL,
	authorization_code int8 NULL,
	currency varchar(4) NULL,
	customer_document_type varchar(10) NULL,
	customer_document_number varchar(30) NULL,
	payment_type varchar(20) NULL,
	reason_code int4 NULL,
	reason_message varchar(255) NULL,
	status varchar(10) NULL,
	seller_id uuid NOT NULL,
	transaction_amount float8 NULL,
	received_at timestamp NULL,
	authorized_at timestamp NULL,
	CONSTRAINT payments_pkey PRIMARY KEY (payment_id)
);


CREATE TABLE sellers (
	seller_id uuid NOT NULL,
	api_key uuid NOT NULL,
	client_id varchar(255) NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT sellers_pkey PRIMARY KEY (seller_id),
	CONSTRAINT uk_hibm50c5tstghpg4w8gapd38a UNIQUE (api_key),
	CONSTRAINT uk_qdu3n4dx18f5t7mnvwaiu4oex UNIQUE (client_id)
);