export interface ConfigEnv {
    environment: string,
	db: {
        connection: string
    }, 
	google: {
		clientID: string,
		clientSecret: string,
		callbackURL: string;
	},
    express: {
        port: number;
    },
    sessionSecret: string
};